node {
    stage("Checkout") {
        checkout(
                [
                        $class: 'GitSCM',
                        branches: [[name: "origin/master"]],
                        doGenerateSubmoduleConfigurations: false,
                        extensions: [], submoduleCfg: [],
                        userRemoteConfigs: [[credentialsId: 'gitea-user-name', url: 'http://119.4.240.179:31080/loeyae/loeyae-cloud.git']]
                ]
        )
    }
    stage("deploy") {
        def buildId = 1
        try {
            buildId = Integer.valueOf(currentBuild.id)
        } catch(exc) {
        }
        def imageTag = "hub.bys.cd/library/loeyae_gateway:${buildId}"
        def latestTag = "hub.bys.cd/library/loeyae_gateway:latest"
        withCredentials([dockerCert(credentialsId: 'docker-client', variable: 'DOCKER_CERT_PATH')]) {
            try {
                if (buildId != 1) {
                    def prevId = buildId - 1
                    def prevImageTag = "hub.bys.cd/library/loeyae_gateway:${prevId}"
                    sh """
                    docker rmi $prevImageTag
                    """
                }
            } catch (exc) {
                print(exc.getMessage())
            }
            try {
                sh """
                  docker pull $latestTag
                  docker tag $latestTag $imageTag
                  docker push $imageTag  
                  """
            }
            catch (exc) {
                println("pull image failure")
                print(exc.getMessage())
                currentBuild.result = 'FAILURE'
            }
            try {
                sh """
                docker rmi $latestTag
                """
            } catch (exc) {
                print(exc.getMessage())
            }
        }
        def source = "loeyae-gateway/src/main/jenkins/loeyae-gateway.yml"
        sh "sed -e 's#{TAG}#${buildId}#g' ${source} > deployment.yml"
        sh "kubectl apply -f deployment.yml"
    }
}
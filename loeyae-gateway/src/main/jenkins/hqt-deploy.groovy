node {
    stage("Checkout") {
        checkout(
                [
                        $class: 'GitSCM',
                        branches: [[name: "origin/master"]],
                        doGenerateSubmoduleConfigurations: false,
                        extensions: [], submoduleCfg: [],
                        userRemoteConfigs: [[credentialsId: 'gitea-user-name', url: 'https://github.com/loeyae/loeyae-cloud.git']]
                ]
        )
    }
    stage("deploy") {
        def buildId = 1
        try {
            buildId = Integer.valueOf(currentBuild.id)
        } catch(exc) {
        }
        def imageTag = "loeyae_gateway:${buildId}"
        def latestTag = "loeyae_gateway:latest"
        withCredentials([dockerCert(credentialsId: 'docker-client', variable: 'DOCKER_CERT_PATH')]) {
            try {
                sh """
                    docker rmi \$(docker images | grep "loeyae_gateway" | awk "{print \$3}") -f
                """
            } catch(exc) {
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
        def source = "loeyae-gateway/src/main/jenkins/hqt-gateway.yml"
        sh "sed -e 's#{TAG}#${buildId}#g;s#{ENV}#hqt_test#g' ${source} > deployment.yml"
        sh "kubectl apply -f deployment.yml"
    }
    stage("Post") {
        if (currentBuild.currentResult == 'SUCCESS') {
            echo "sucess"
            build job: 'service_gateway-deploy-prod', parameters: [text(name: 'IMAGE_TAG', value: BUILD_ID)], propagate: false, quietPeriod: 9, wait: false
        }
    }
}
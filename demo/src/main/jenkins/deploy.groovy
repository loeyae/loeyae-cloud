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
        def imageTag = "loeyae_demo:${buildId}"
        def latestTag = "loeyae_demo:latest"
        withCredentials([dockerCert(credentialsId: 'docker-client', variable: 'DOCKER_CERT_PATH')]) {
            try {
                sh """
                    docker rmi \$(docker images | grep "loeyae_demo" | awk "{print \$3}") -f
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
        def source = "demo/src/main/jenkins/loeyae-demo.yml"
        sh "sed -e 's#{TAG}#${buildId}#g;s#{ENV}#test#g' ${source} > deployment.yml"
        sh "kubectl apply -f deployment.yml"
    }
    stage("Post") {
        if (currentBuild.currentResult == 'SUCCESS') {
            echo "sucess"
            build job: 'loeyae_demo-deploy-prod', parameters: [text(name: 'IMAGE_TAG', value: BUILD_ID)], propagate: false, quietPeriod: 9, wait: false
        }
    }
}
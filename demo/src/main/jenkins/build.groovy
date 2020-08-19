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

    stage("Package") {
        withCredentials([dockerCert(credentialsId: 'docker-client', variable: 'DOCKER_CERT_PATH')]) {
            sh """
                export JAVA_HOME=/mnt/data/jenkins_home/jdk
                cd demo/
                mvn -f pom.xml clean package -Dautoconfig.skip=true -Dmaven.test.skip=true
               """
        }
    }
    stage("Image push") {
        def latestTag = "loeyae_demo:latest"
        withCredentials([dockerCert(credentialsId: 'docker-client', variable: 'DOCKER_CERT_PATH')]) {
            try {
                sh """
              docker tag loeyae_demo:latest $latestTag
              docker push $latestTag
              """
            }
            catch (exc) {
                println("Push image failure")
                print(exc.getMessage())
                currentBuild.result = 'FAILURE'
            }
            sh """
            docker rmi $latestTag
            """
        }
    }
}

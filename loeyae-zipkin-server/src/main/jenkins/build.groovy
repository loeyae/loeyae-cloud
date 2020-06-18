node {
    stage("Checkout") {
        checkout(
                [
                        $class: 'GitSCM',
                        branches: [[name: "origin/master"]],
                        doGenerateSubmoduleConfigurations: false,
                        extensions: [], submoduleCfg: [],
                        userRemoteConfigs: [[credentialsId: 'gitea-user-name', url: 'http://119.4.240.179:31080/bys-cd/loeyae-cloud.git']]
                ]
        )
    }

    stage("Package") {
        withCredentials([dockerCert(credentialsId: 'docker-client', variable: 'DOCKER_CERT_PATH')]) {
            sh """
                export JAVA_HOME=/mnt/data/jenkins_home/jdk
                cd loeyae-zipkin-server/
                mvn -f pom.xml clean package -Dautoconfig.skip=true -Dmaven.test.skip=true
               """
        }
    }
    stage("Image push") {
        def latestTag = "hub.bys.cd/library/loeyae_zipkin_server:latest"
        withCredentials([dockerCert(credentialsId: 'docker-client', variable: 'DOCKER_CERT_PATH')]) {
            try {
                sh """
              docker tag loeyae_zipkin_server:latest $latestTag
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

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
    stage("deploy") {
        def buildId = 1
        try {
            buildId = Integer.valueOf(currentBuild.id)
        } catch(exc) {
        }
        def imageTag = "hub.bys.cd/library/loeyae_zipkin_server:${buildId}"
        def latestTag = "hub.bys.cd/library/loeyae_zipkin_server:latest"
        withCredentials([dockerCert(credentialsId: 'docker-client', variable: 'DOCKER_CERT_PATH')]) {
            try {
                sh """
                    docker rmi \$(docker images | grep "loeyae_zipkin_server" | awk "{print \$3}") -f
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
        def source = "loeyae-zipkin-server/src/main/jenkins/loeyae-zipkin-server.yml"
        sh "sed -e 's#{TAG}#${buildId}#g;s#{MYSQL_USER}#bys#g;s#{MYSQL_PASS}#200519@Bys#g;s#{MYSQL_HOST}#mysql-mysqlha-0.mysql-mysqlha.mysql#g;s#{MYSQL_PORT}#3306#g;s#{ENV}#test#g' ${source} > deployment.yml"
        sh "kubectl apply -f deployment.yml"
    }
    stage("Post") {
        if (currentBuild.currentResult == 'SUCCESS') {
            echo "sucess"
            build job: 'loeyae_zipkin_server-deploy-prod', parameters: [text(name: 'IMAGE_TAG', value: BUILD_ID)], propagate: false, quietPeriod: 9, wait: false
        }
    }
}
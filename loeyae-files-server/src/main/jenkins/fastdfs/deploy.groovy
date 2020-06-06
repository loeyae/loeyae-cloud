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
        def source = "loeyae-files-server/src/main/jenkins/fastdfs/loeyae-fastdfs.yml"
        sh "sed -e 's#{ENV}#test#g' ${source} > deployment.yml"
        sh "kubectl apply -f deployment.yml"
    }
}
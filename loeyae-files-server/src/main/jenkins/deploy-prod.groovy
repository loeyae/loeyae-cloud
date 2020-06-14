node {
    properties([
            parameters([
                    string(defaultValue: '', description: 'image tag', name: 'IMAGE_TAG', trim: true),
                    string(defaultValue: 'prod', description: 'deploy env', name: 'ENV_LABEL', trim: true)
            ])
    ])
    stage('Approval') {
        timeout(time:5, unit:'HOURS') {
            input(id: 'Approval', message: '是否批准', ok: '批准', submitter: 'user')
        }
    }
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
        def buildId = params.IMAGE_TAG
        if (!buildId) {
            throw new RuntimeException("Image Tag Not Setting")
        }
        def source = "loeyae-files-server/src/main/jenkins/loeyae-files-server-prod.yml"
        sh "sed -e 's#{TAG}#${buildId}#g;s#{ENV}#${params.ENV_LABEL}#g' ${source} > deployment.yml"
        sh "kubectl apply -f deployment.yml --kubeconfig=/home/bys/.kube/hq.config"
    }
}
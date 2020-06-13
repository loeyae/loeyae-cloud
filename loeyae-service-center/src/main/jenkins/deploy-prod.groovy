node {
    properties([
            parameters([
                    string(defaultValue: '', description: 'image tag', name: 'IMAGE_TAG', trim: true),
                    string(defaultValue: 'prod', description: 'deploy env', name: 'ENV_LABEL', trim: true),
                    gitParameter(branch: '', branchFilter: 'origin/(.*)', defaultValue: 'develop', description: '', listSize: '10', name: 'BRANCH', quickFilterEnabled: false, selectedValue: 'DEFAULT', sortMode: 'NONE', tagFilter: '*', type: 'PT_BRANCH', useRepository: 'http://119.4.240.179:31080/huaxin/HqDcxtServer.git')
            ])
    ])
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
        def source = "loeyae-service-center/src/main/jenkins/loeyae-service-center-prod.yml"
        sh "sed -e 's#{TAG}#${buildId}#g;s#{ENV}#${params.ENV_LABEL}#g' ${source} > deployment.yml"
        sh "kubectl apply -f deployment.yml --kubeconfig=/home/bys/.kube/hq.config"
    }
}
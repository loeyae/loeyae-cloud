node {
    properties([
            parameters([
                    string(defaultValue: '', description: 'image tag', name: 'IMAGE_TAG', trim: true),
                    string(defaultValue: 'prod', description: 'deploy env', name: 'ENV_LABEL', trim: true),
                    gitParameter(branch: '', branchFilter: 'origin/(.*)', defaultValue: 'develop', description: '', listSize: '10', name: 'BRANCH', quickFilterEnabled: false, selectedValue: 'DEFAULT', sortMode: 'NONE', tagFilter: '*', type: 'PT_BRANCH', useRepository: 'https://github.com/loeyae/loeyae-cloud.git')
            ])
    ])
    stage('Approval') {
        timeout(time:5, unit:'HOURS') {
            input(id: 'Approval', message: 'Approval or Abort', ok: 'Approval', submitter: 'user')
        }
    }
    stage("Checkout") {
        checkout(
                [
                        $class: 'GitSCM',
                        branches: [[name: "origin/master"]],
                        doGenerateSubmoduleConfigurations: false,
                        extensions: [], submoduleCfg: [],
                        userRemoteConfigs: [[credentialsId: 'git-user-name', url: 'https://github.com/loeyae/loeyae-cloud.git']]
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
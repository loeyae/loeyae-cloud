node {
    properties([
            parameters([
                    string(defaultValue: '', description: 'image tag', name: 'IMAGE_TAG', trim: true),
                    string(defaultValue: 'prod', description: 'deploy env', name: 'ENV_LABEL', trim: true)
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
        def imageTag = "loeyae_zipkin_server:${buildId}"
        def source = "loeyae-zipkin-server/src/main/jenkins/loeyae-zipkin-server-prod.yml"
        sh "sed -e 's#{TAG}#${buildId}#g;s#{ENV}#${params.ENV_LABEL}#g' ${source} > deployment.yml"
        sh "kubectl apply -f deployment.yml --kubeconfig=/home/bys/.kube/hq.config"
    }
}
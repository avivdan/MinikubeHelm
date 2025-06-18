pipeline {
    agent any
    environment {
        KUBERNETES_SERVER = 'https://192.168.49.2:8443'
        KUBERNETES_TOKEN = credentials('k8s-token') // Reference the stored token
    }
    stages {
        stage('remove last my-nginx chart'){
            steps {
                sh 'helm uninstall my-nginx --namespace web --ignore-not-found'
            }
        }
        stage('Verify Deployment') {
            steps {
                sh 'kubectl get pods'
            }
        }
        stage('Verify Docker') {
            steps {
                sh 'docker ps -a'
            }
        }
        stage('Verify helm') {
            steps {
                sh 'helm list'
            }
        }
        stage('Deploy Nginx') {
            steps {
                sh '''
                helm repo add bitnami https://charts.bitnami.com/bitnami
                helm repo update
                helm upgrade --install my-nginx bitnami/nginx \
                  --namespace web \
                  --create-namespace \
                  --set service.type=NodePort \
                  --set service.nodePorts.http=31913 \
                  --wait --timeout 2m
                helm list -n web
                '''
            }
        }

    }
}

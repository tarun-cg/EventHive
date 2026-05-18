pipeline {
    agent any
    
    environment {
        DOCKER_HUB_USER = "tarunk0654"
        IMAGE_NAME      = "eventhive"
        IMAGE_TAG       = "${env.BUILD_NUMBER}"
        K8S_CRED_ID     = "k8s-config" // ID you gave your kubeconfig in Jenkins
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Docker Build & Push') {
            steps {
                script {
                    // This builds the image using the Dockerfile above
                    docker.withRegistry('', 'docker-hub-creds') {
                        def appImage = docker.build("${DOCKER_HUB_USER}/${IMAGE_NAME}:${IMAGE_TAG}")
                        appImage.push()
                        appImage.push('latest')
                    }
                }
            }
        }

        stage('Deploy to MicroK8s') {
            steps {
                // Update the YAML file with the new image version before deploying
                sh "sed -i 's|image:.*|image: ${DOCKER_HUB_USER}/${IMAGE_NAME}:${IMAGE_TAG}|g' k8s-deployment.yaml"
                
                kubernetesDeploy(
                    configs: 'k8s-deployment.yaml',
                    kubeconfigId: "${K8S_CRED_ID}"
                )
            }
        }
    }
}

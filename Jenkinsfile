pipeline {
    agent any
    stages {
        stage('Build project') {
            steps {
                git 'https://github.com/zeeshanzsh/productService.git'
                sh '/opt/homebrew/bin/mvn clean install'
            }
        }
        stage('Docker build image'){
            steps{
                script{
                    sh 'docker build -t zohanizna/product-service .'
                }
            }
        }
        stage('Push Image to Docker-Hub'){
            steps{
                script{
                // Load docker hub creds from secret Text
                withCredentials([string(credentialsId: 'dockerCreds', variable: 'dockerCreds')]) {
                         sh "docker login -u zohanizna -p $dockerCreds"
                }
                // Block to push to docker-hub
                  sh 'docker push zohanizna/product-service'
                }
            }
        }
        stage("Deploy K8 productService"){
            steps{
                script{
                  withCredentials([kubeconfig(credentialsId: 'kubeconfig')]) {
                    // Print the current directory and contents for debugging
                    sh 'pwd'
                    sh 'ls -la'

                    // Apply the Kubernetes deployment manifest
                    sh 'kubectl apply -f deployment.yaml'

                    // Get information about the deployed pods
                    sh 'kubectl get pods'
                 }
                }
            }
             }

    }
}
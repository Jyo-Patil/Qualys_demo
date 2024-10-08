pipeline {
    agent {label 'master'}
    
    stages {
        stage('Checkout') { 
            steps {
                    sh "mkdir -p checkout_dir"
                    dir("checkout_dir") {
                        git branch: "master", url: 'https://github.com/pmgupte/webmon.git'
                    }
            }
        }
        
        stage('Build Docker Image') {
            steps {
                dir("checkout_dir") {
                    sh "docker build -t prabhasgupte/webmon:latest  . > docker_output"
                }
            }
        }
        
        stage('Extract Image Id') {
            steps {
                script {
                    def IMAGE_ID = sh(script: "docker images | grep -E '^prabhasgupte/webmon.*latest' | head -1 | awk '{print \$3}'", returnStdout: true).trim()
                    echo "Image Id extracted: ${IMAGE_ID}"
                    env.IMAGE_ID = IMAGE_ID
                }
            }
        }
        
        stage('Validate With Qualys') {
            steps {
                echo "Image Id to get vulns: ${env.IMAGE_ID}"
                getImageVulnsFromQualys imageIds: env.IMAGE_ID, useGlobalConfig: true
            }
        }
        
        stage('Push to registry') {
            steps {
                echo "Dummy Step: push validated image to registry"
            }
        }
    } // stages
    post {
        always {
            sh "docker rmi -f prabhasgupte/webmon:latest"
        }
    }
}

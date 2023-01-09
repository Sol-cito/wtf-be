env.environment

pipeline {
    agent any

    stages {
        stage('Set Environment variables') {
            steps {
                script {
                    if(env.BRANCH_NAME == 'develop') {
                        env.environment = 'dev'
                    }
                    if(env.BRANCH_NAME == 'prod') {
                        env.environment = 'prod'
                    }
                    echo "Current build/deploy environment is ${env.environment}"
                }
            }
        }

        stage('Pipeline Health Check') {
            steps {
                echo "Hello WTF Jenkins!!!!!"
            }
        }
        stage('Build') {
            steps {
                echo "Build start by shell script"
                dir("/var/lib/jenkins/jobs/wtf-be-${env.environment}/workspace/deploy-script") {
                    sh 'bash build.sh'
                }
            }
        }
        stage('New Instance Health check') {
            steps {
                echo "Health check start by shell script"
                dir("/var/lib/jenkins/jobs/wtf-be-${env.environment}/workspace/deploy-script") {
                    sh 'bash healthCheck.sh'
                }
            }
        }
        stage('Nginx Port Switching') {
            steps {
                echo "Switching by shell script"
                dir("/var/lib/jenkins/jobs/wtf-be-${env.environment}/workspace/deploy-script") {
                    sh 'bash portSwitch.sh'
                }
            }
        }
        stage('Kill Previous Instance Process') {
            steps {
                echo "Kill Previous Instance Process by shell script"
                dir("/var/lib/jenkins/jobs/wtf-be-${env.environment}/workspace/deploy-script") {
                    sh 'bash killPreviousProcess.sh'
                }
            }
        }
    }
}
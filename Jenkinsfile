pipeline {
    agent any

    stages {
        stage('Set Environment variables') {
            steps {
                script {
                    echo "Current profile is ${PROFILE}"
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
                dir("/var/lib/jenkins/jobs/wtf-be-${PROFILE}/workspace/deploy-script") {
                    sh 'bash build.sh'
                }
            }
        }
        stage('New Instance Health check') {
            steps {
                echo "Health check start by shell script"
                dir("/var/lib/jenkins/jobs/wtf-be-${PROFILE}/workspace/deploy-script") {
                    sh 'bash healthCheck.sh'
                }
            }
        }
        stage('Nginx Port Switching') {
            steps {
                echo "Switching by shell script"
                dir("/var/lib/jenkins/jobs/wtf-be-${PROFILE}/workspace/deploy-script") {
                    sh 'bash portSwitch.sh'
                }
            }
        }
        stage('Kill Previous Instance Process') {
            steps {
                echo "Kill Previous Instance Process by shell script"
                dir("/var/lib/jenkins/jobs/wtf-be-${PROFILE}/workspace/deploy-script") {
                    sh 'bash killPreviousProcess.sh'
                }
            }
        }
    }
}
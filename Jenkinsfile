pipeline {
    agent any

    stages {
        stage('Check parameterized Profile') {
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
                    sh 'bash build.sh ${PROFILE}'
                }
            }
        }
        stage('New Instance Health check') {
            steps {
                echo "Health check start by shell script"
                dir("/var/lib/jenkins/jobs/wtf-be-${PROFILE}/workspace/deploy-script") {
                    sh 'bash healthCheck.sh ${PROFILE}'
                }
            }
        }
        stage('Nginx Port Switching') {
            steps {
                echo "Switching by shell script"
                dir("/var/lib/jenkins/jobs/wtf-be-${PROFILE}/workspace/deploy-script") {
                    sh 'bash portSwitchAndKillPreviousProcess.sh ${PROFILE}'
                }
            }
        }
    }
}
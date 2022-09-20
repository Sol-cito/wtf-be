pipeline {
    agent any

    stages {
        stage('Pipeline Health Check') {
            steps {
                echo "Hello WTF Jenkins!!!!!"
            }
        }

        stage('Build') {
            steps {
                echo "Build start by shell script"
                sh 'cd /var/lib/jenkins/jobs/wtf-be-dev/workspace/'
                sh 'bash deploy.sh'
            }
        }

        stage('Health check') {
            steps {
                sh 'curl -m 10 http://127.0.0.1:10083/health'
            }
        }
    }
}
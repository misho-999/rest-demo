pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                sh 'mvn -B -DskipTests clean package'
            }
        }
        stage('Test') {
            steps {
                sh 'mvn test'
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }
        stage('Deploy') {
            when {
              expression {
                currentBuild.result == null || currentBuild.result == 'SUCCESS' 
              }
            }
            steps {
                sh 'make publish'
            }
        }
//         stage('Deploy') {
//             steps {
//                 script {
//                     def remote = [: ]
//                     remote.name = "185.228.26.15"
//                         remote.host = "185.228.26.15"
//                         remote.port = 8080
//                         remote.user = 'root'
//                         remote.password = ''
//                         remote.logLevel = "INFO"
//
//                         sh 'sudo systemctl stop rest-demo'
//                         sh 'sudo systemctl start rest-demo'
//                 }
//             }
//         }
    }
}

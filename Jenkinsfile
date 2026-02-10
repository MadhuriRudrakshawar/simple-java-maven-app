pipeline {
    agent any

    tools {
        maven 'Maven_3'
    }

    parameters {
        booleanParam(
            name: 'RUN_UI_TESTS',
            defaultValue: false,
            description: 'Run Selenium UI tests'
        )
    }



environment {
        GITHUB_TOKEN = credentials('github-token')
    }




    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build and Unit Tests') {
            steps {
                bat 'mvn -B clean test package'
            }
        }

        stage('UI Tests (Selenium)') {
            when {
                expression { return params.RUN_UI_TESTS }
            }
            steps {
                bat 'mvn -B verify -DskipUnitTests=true'
            }
        }

         stage('Secure Step') {
                    steps {
                        sh 'echo "Token length is ${#GITHUB_TOKEN}"'
                    }
                }
         }
    }

    post {
        always {
            junit allowEmptyResults: true, testResults: '''
                target/surefire-reports/*.xml
                target/failsafe-reports/*.xml
            '''

            archiveArtifacts artifacts: 'target/screenshots/**/*.png', allowEmptyArchive: true
        }
    }

}

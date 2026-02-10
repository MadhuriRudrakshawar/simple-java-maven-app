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
                bat 'mvn -B clean verify'
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
                bat 'echo "Token length is ${#GITHUB_TOKEN}"'
            }
        }

        stage('SonarQube Analysis') {
                    steps {
                        withSonarQubeEnv('LocalSonar') {
                            bat '''
                              mvn sonar:sonar \
                                -Dsonar.projectKey=simple-java-maven-app
                            '''
                        }
                    }
                }

                stage('Quality Gate') {
                    steps {
                        timeout(time: 2, unit: 'MINUTES') {
                            waitForQualityGate abortPipeline: true
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


            publishHTML(target: [
                        reportDir: 'target/site/jacoco',
                        reportFiles: 'index.html',
                        reportName: 'JaCoCo Code Coverage',
                        keepAll: true,
                        alwaysLinkToLastBuild: true
                    ])

        }
    }
}

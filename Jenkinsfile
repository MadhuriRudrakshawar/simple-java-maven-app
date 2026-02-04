pipeline {
  agent any

  tools {
    maven 'Maven_3'
  }

  stages {
    stage('Checkout') {
      steps { checkout scm }
    }

    stage('Build and Test') {
      steps {
        bat 'mvn -version'
        bat 'mvn clean test package'
      }
    }
  }
}

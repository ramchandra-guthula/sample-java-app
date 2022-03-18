pipeline {

    agent any

    stages {
        stage ('Clone') {
            steps {
                //git credentialsId: 'ram github credentials', url: 'https://github.com/ramchandra-guthula/sample-java-app.git'
                git  branch: 'master', url: 'https://github.com/ramchandra-guthula/sample-java-app.git'
            }
        }

        stage ('Init') {
            steps {
                sh '''
                ls -lrt $WORKSPACE
                '''
            }
        }

        stage ('Build Project') {
            tools {
                    maven 'maven-3.8.1'
            }
            steps {
                sh " mvn clean install"
            }
        }

        stage('Get Ansible Scripts') {
            steps {
                // We are getting anisble scripts to create an Instance and deploy the war file to tomcat
                // We can even add the files in the same repo by creatuing seperate folder instead of cloneing from a separate repo
                git branch: 'main',  url: 'https://github.com/ramchandra-guthula/ansible_practice.git'
            }
        }

        stage('Deploy WAR file') {
            steps {
                script {
                   dir("$WORKSPACE/ec2_tomcat/") {  // This to excute below steps from the $WORKSPACE/ec2_tomcat directory
                         ansiColor('xterm'){ // We need to install ansiColour plugin to use this ansiColour
                                 ansiblePlaybook credentialsId: 'jenkins_slave_ssh_keys', installation: 'ansible-2.9', colorized: true, playbook: '$WORKSPACE/ec2_tomcat/site.yaml'
                                 // We need to install Ansible plugin and configure Ansible with out any executable, we must install ansible in local
                             currentBuild.displayName = "# {BUILD_NUMBER}-java-app"
                                 currentBuild.description = "This is sample app"
                        }
                    }
                }
            }
        }
        
        stage ('Clean workspace'){
            steps {
                cleanWs()
            }
        }
    }
}

pipeline {
    agent any
    stages {
	    
        stage ('Clone the Project'){
            steps {
          git credentialsId: 'ram github credentials', url: 'https://github.com/ramchandra-guthula/sample-java-app.git'
          }
        }
	    
        stage ('Sample verification') {
            steps {
                sh ''' 
		ls -lrt $WORKSPACE
                '''
            }
        }
	    
        stage ('Build Project'){
            tools {
                maven 'maven-3.8.1'
                }
            steps {
                sh " mvn clean install"
            }
        }
	    
	stage('Get Ansible Scripts') {
	     steps {
		git branch: 'main', credentialsId: 'ram github credentials', url: 'https://github.com/ramchandra-guthula/ansible_practice.git'
		    }
		}
		
	stage('Deploy WAR file') {
	     steps {
		script {
		   dir("$WORKSPACE/ec2_tomcat/") {  // This to excute below steps from the $WORKSPACE/ec2_tomcat directory
		      	 ansiColor('xterm'){ // We need to install ansiColour plugin to use this ansiColour
				 ansiblePlaybook credentialsId: 'devops_ssh_key', installation: 'ansible-2.9', playbook: '$WORKSPACE/ec2_tomcat/site.yaml'
				 // We need to install Ansible plugin and configure Ansible with out any executable, we must install ansible in local
				 currentBuild.displayName = "my-app"
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

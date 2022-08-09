def COLOR_MAP = [
'SUCCESS': 'good',
'FAILURE': 'danger',
]
pipeline{
    
    agent any
    
    environment {
        registryName = "azurefst"
        registryUrl = "azurefst.azurecr.io"
        registryCredential = "ACR1"
        dockerImage = ''
    }
    
    stages {
        
        stage ('checkout') {
            steps{
                checkout([$class: 'GitSCM', branches: [[name: '*/master']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[url: 'https://github.com/akannan1087/myPythonDockerRepo']]])
            }
            
        }
        stage("Maven Build"){
            environment {
                mavenHome =  tool name: "maven-3.6.1", type: "maven"
                mavenCMD = "${mavenHome}/bin/mvn"
                }
                steps {
                    sh "${mavenCMD} clean package"
                    }
        }
        
       stage ('Build Docker image') {
            steps {
                script {
                    dockerImage = docker.build registryName
                }
            }
        }
    
        stage('Upload Image to ACR') {
            steps{   
                script {
                    docker.withRegistry( "http://${registryUrl}", registryCredential ) {
                        dockerImage.push()
                        
                    }
                    
                }
                
            }
            
        }
        stage('stop previous containers') {
            steps {
                sh 'docker ps -f name=mypythonContainer -q | xargs --no-run-if-empty docker container stop'
                sh 'docker container ls -a -fname=mypythonContainer -q | xargs -r docker container rm'
                
            }
            
        }
        stage('Docker Run') {
            steps{
                script {
                    sh 'docker run -d -p 8096:5000 --rm --name mypythonContainer ${registryUrl}/${registryName}'
                    
                }
                
            }
            
        }
    }
    
}
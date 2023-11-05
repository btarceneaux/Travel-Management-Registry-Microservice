pipeline {
    agent any 

    triggers {
        pollSCM('* * * * *')
    }
    // Got permission denied while trying to connect to the Docker daemon socket at unix.
    // sudo usermod -a -G docker jenkins
    // restart jenkins server ->  sudo service jenkins restart
    stages {
        
        stage('Maven Compile') {
            steps {
                echo '----------------- Compiling project ----------'
                sh 'mvn clean compile'
            }
        }
        
         stage('Maven Test') {
            steps {
                echo '----------------- Testing project ----------'
                sh 'mvn clean test'
            }
        }
        
        stage('Maven Build') {
             steps {
                echo '----------------- Building project ----------'
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Docker Build') {
            steps {
                echo '----------------- Building docker image ----------'
                sh '''
                    docker image build -t api-registry-webservice .
                '''
            }
        }

        stage('Docker Deploy') {
            steps {
                echo '----------------- Deploying docker image ----------'
                sh '''
                 (if  [ $(docker ps -a | grep api-registry-webservice | cut -d " " -f1) ]; then \
                        echo $(docker rm -f api-registry-webservice); \
                        echo "---------------- successfully removed api-registry-webservice ----------------"
                     else \
                    echo OK; \
                 fi;);
            docker container run --restart always --name api-registry-webservice -p 9001:9001 -d api-registry-webservice && docker network connect travel-management-network
            '''
            }
        }
    }
}
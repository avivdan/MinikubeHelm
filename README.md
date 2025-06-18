## Installation
```bash
kubectl create sa jenkins
kubectl create clusterrolebinding jenkins --clusterrole=cluster-admin --serviceaccount=default:jenkins
kubectl create token jenkins | tee pass.txt
sudo usermod -aG $(id -gn YOUR_USERNAME) jenkins # for the kubernets
```

Add kubernetes plugin 

Add the pass.txt as Jenkins secret text credentials 


### check output of : 
```bash
aviv_docker@blue:~$ kubectl config view

apiVersion: v1
clusters:
- cluster:
    certificate-authority: /home/aviv_docker/.minikube/ca.crt
    extensions:
    - extension:
        last-update: Wed, 18 Jun 2025 20:26:23 IDT
        provider: minikube.sigs.k8s.io
        version: v1.35.0
      name: cluster_info
    server: https://192.168.49.2:8443 <------------------------------ Note this
  name: minikube
contexts:
- context:
    cluster: minikube
    extensions:
    - extension:
        last-update: Wed, 18 Jun 2025 20:26:23 IDT
        provider: minikube.sigs.k8s.io
        version: v1.35.0
      name: context_info
    namespace: default
    user: minikube
  name: minikube
current-context: minikube
kind: Config
preferences: {}
users:
- name: minikube
  user:
    client-certificate: /home/aviv_docker/.minikube/profiles/minikube/client.crt
    client-key: /home/aviv_docker/.minikube/profiles/minikube/client.key
```
The IP should be Kubernetes server
and the credentials 

```groovy
    environment {
        KUBERNETES_SERVER = 'https://192.168.49.2:8443'
        KUBERNETES_TOKEN = credentials('k8s-token') // Reference the stored token
    }
```

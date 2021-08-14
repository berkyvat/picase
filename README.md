# picase
Create an AWS account.
Create a S3 bucket with proper access policies.
Write a simple application containing following HTTP endpoints. You can use any programming language.
- GET /xxx/list: This endpoint will return objects on the S3 bucket created in step #2.
- POST /xxx/put: This endpoint will save given JSON data to the S3 bucket and returns the S3 object key.
- GET /picus/get/{key}: This endpoint will return content of given key from S3.
Build a docker image for your application with Dockerfile.
Use Github actions to create pipeline to build and test.
Deploy your application to AWS ECS service and configure AWS IAM roles/policies properly. You can use either AWS Fargate or EC2.
Write a README file explaining all details.

Yukarıda verilen case study kapsamında;

AWS account oluşturuldu
S3 Bucket oluşturuldu
Api için java-spring-boot-maven kullanıldı
İstenilen endpointler ve işlevleri oluşturuldu
  xxx/list endpointi s3 bucket objectlerini dönmektedir
  xxx/put Verilen json formatındaki datayı oluşturulan S3 Bucket a kaydetmektedir
  xxx/get/{key} Verilen key ile eşleşen objenin içeriğini dönmektedir
Dockerfile oluşturuldu
Github actions kullanılarak oluşuturlan pipeline sırasıyla build-test-deploy(dockerhub-aws ecs) adımlarından oluşmaktadır
Oluşturulan Docker image ECS üzerinden EC2 instance da koşmak üzere gerekli configurasyon ayarları yapıldı. Ancak;
  Github actions'da görebileceğiniz "No Container Instances were found in your cluster" hatası ile karşılaşıldı. Ekran görüntüsüne eklerde ulaşılabilir. Aslında EC2 instance'ı 
  başarılı şekilde oluşturup ECS ile konfigurasyonlarını yapmıştım. Bu sorunun neden kaynaklandığı ile ilgili fikrim IAM role konfigurasyonu ile ilgili olabileceği yönünde gelişti.
  Bu nedenle yeni bir EC2 instance yaratma yolunu seçtim. Ancak, "this account is currently blocked and not recognized as a valid account" hatası ile karşılaşıldı.Ekran görüntüsüne 
  eklerde ulaşılabilir. Sebebini bilmiyorum ama bu çalışmayı yaparken hem github hesabım suspend oldu hem aws hesabım, bunlar sıfırdan yeni hesaplar açıp geliştirmeye devam etmem
  ile atlatıldı. Hali hazırda yeterince geciktiğim için tekrar bütün aşamalara baştan başlamamak ve şu anki hali ile size gönderme kararı aldım.
  

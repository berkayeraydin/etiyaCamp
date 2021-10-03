# ReCapProject <img align="left" alt="GitHub" width="26px" src="https://raw.githubusercontent.com/github/explore/78df643247d429f6cc873026c0622819ad797942/topics/github/github.png" />
  ## Table of contents 
💡 [General info](#general-info)

💡 [About](#about)

💡 [Entity Relationship Diagram](#entity-relationship-diagram)

💡 [Requirements](#requirements)

💡 [Technologies](#technologies)

💡 [Linkedin](#linkedin)

## General info
#### 🖍 Backend of a car rental website for Etiya academy.
- We designed the project of the `backend service of a simple car rental site` and tried to comply with `SOLID rules` in the project. Using `SpringBoot`, we performed `database operations with API's`. We have associated our tables with `JpaRepository`, which is part of the Spring framework.

<img align="left" alt="Java" width="26px" src="https://classes.engineering.wustl.edu/cse231/core/images/2/26/Java.png" />
<img align="left" alt="SQL" width="26px" src="https://uploads.toptal.io/blog/category/logo/60/sql.png" />
<img align="left" alt="PostGreSQL" width="26px" src="https://raw.githubusercontent.com/github/explore/80688e429a7d4ef2fca1e82350fe8e3517d3494d/topics/postgresql/postgresql.png" />
<img align="left" alt="Swagger" width="26px" src="https://www.form.io/sites/default/files/2018-08/swagger-300.jpg" />
<img align="left" alt="SpringBoot" width="26px" src="https://www.instana.com/media/spring_boot_logo.png" /><br/><br/>

## About
### 🖍 Entities
- It is the layer where `Objects`, `Dto's` and `Requests` are defined.<br/> [Entities kısmı için tıklayınız.](https://github.com/eraydin61/etiyaCamp/tree/main/ReCapProject/src/main/java/com/etiya/ReCapProject/entities)

### 🖍 Data Access
- It is the layer that connects with the database. Tables are created using JpaRepository.<br/> [DataAccess kısmı için tıklayınız.](https://github.com/eraydin61/etiyaCamp/tree/main/ReCapProject/src/main/java/com/etiya/ReCapProject/dataAccess)

### 🖍 Business
- It is the layer where `business rules` are defined and controlled. When a command is received by the program, what operations it should do and which set of rules it should go through are defined here.<br/> [Business kısmı için tıklayınız.](https://github.com/eraydin61/etiyaCamp/tree/main/ReCapProject/src/main/java/com/etiya/ReCapProject/business)

### 🖍 API
- It is the layer where we run our codes with `Spring boot`. <br/>[Api kısmı için tıklayınız.](https://github.com/eraydin61/etiyaCamp/tree/main/ReCapProject/src/main/java/com/etiya/ReCapProject/api)

### 🖍 Core
- It is the layer that `connects with external  services`.Generally we used this layer to use some `rules` or `utilities`. <br/>[Core kısmı için tıklayınız.](https://github.com/eraydin61/etiyaCamp/tree/main/ReCapProject/src/main/java/com/etiya/ReCapProject/core)

### 🖍 Out Services
- External services <br/>[Out Service kısmı için tıklayınız.](https://github.com/eraydin61/etiyaCamp/tree/main/ReCapProject/src/main/java/com/etiya/ReCapProject/outServices)

## Entity Relationship Diagram
<p align="center">
<img src="./Entity Relationship Diagram.png" width="1000"  />


## Requirements
  
#### ⚙ Steps
1. Yepyeni bir proje oluşturunuz. Adı `ReCapProject` olacak. (Tekrar ve geliştirme projesi) `Entities`, `DataAccess`, `Business` oluşturunuz. Bir araba nesnesi oluşturunuz. `"Car"`, `"Brand"` ve `"Color"` nesnelerini oluşturunuz.(`BrandId,BrandName… Özellik olarak : Id, BrandId, ColorId, ModelYear, DailyPrice, Description` alanlarını ekleyiniz. (Brand = Marka)
2. `Car`, `Brand`, `Color` sınıflarınız için tüm `CRUD` operasyonlarını hazır hale getiriniz. Console'da Tüm CRUD operasyonlarınızı Car, Brand, Model nesneleriniz için `test ediniz`. `GetAll`, `GetById`, `Insert`, `Update`, `Delete`. Arabaları şu bilgiler olacak şekilde listeleyiniz. CarName, BrandName, ColorName, DailyPrice. (İpucu : Dto oluşturup 3 tabloya join yazınız)
3. Core katmanında `Results yapılandırması` yapınız.
4. Kullanıcılar tablosu oluşturunuz. `Users-->Id,FirstName,LastName,Email,Password. Müşteriler tablosu oluşturunuz`. Customers->UserId,CompanyName. `Kullanıcılar ve müşteriler ilişkilidir.` `Arabanın kiralanma bilgisini tutan tablo oluşturunuz`. Rentals->`Id`, `CarId`, `CustomerId`, `RentDate(Kiralama Tarihi)`, `ReturnDate(Teslim Tarihi)`. Araba teslim edilmemişse ReturnDate null'dır. Projenizde bu entity'leri oluşturunuz. `CRUD` operasyonlarını yazınız. Yeni müşteriler ekleyiniz. Arabayı kiralama imkanını kodlayınız. Rental- >Add `Arabanın kiralanabilmesi için arabanın teslim edilmesi gerekmektedir`.
5. `WebAPI katmanını kurunuz`. Business katmanındaki `tüm servislerin Api karşılığını yazınız`. `Swagger’da test ediniz`.
6. `Validation desteği ekleyiniz`.
7. `CarImages` (Araba Resimleri) tablosu oluşturunuz. `(Id,CarId,ImagePath,Date)` Bir arabanın birden fazla resmi olabilir. Api üzerinden arabaya resim ekleyecek sistemi yazınız. Resimler projeniz içerisinde `bir klasörde` tutulacaktır. `Resimler yüklendiği isimle değil, kendi vereceğiniz GUID ile dosyalanacaktır`. Resim silme, güncelleme yetenekleri ekleyiniz. Bir arabanın `en fazla 5` resmi olabilir. Resmin eklendiği tarih sistem tarafından atanacaktır. Bir arabaya ait resimleri listeleme imkanı oluşturunuz. (Liste) Eğer bir `arabaya ait resim yoksa, default bir resim gösteriniz`. Bu resim şirket logonuz olabilir. (Tek elemanlı liste)
8. Brand listesinde herhangi bir marka seçildiğinde, o markaya ait arabaları listeleyiniz. Color listesinde herhangi bir renk seçildiğinde, o renge ait arabaları listeleyiniz. Car listesinde bir arabaya tıklandığında o arabaya ait detay sayfası oluşturunuz. Bu sayfada bu araca ait resimleri de gösteriniz.
9. `Login/Register` yetenekleri getiriniz. Kiralama esnasında müşterinin `findeks puanını sorgulayacak sahte servis` ekleyiniz. Findeks puan aralığı `0-1900` arasındadır. Araçların kiralanabilmesi için `her aracın ayrı ayrı minimum findeks puanı` olmalıdır. Bu puanı olmayan müşteriler araç kiralayamaz. Giriş yapılmışsa müşteri adı ex: "Müsteri İsmi" yazsın. Kullanıcı bilgilerini görüp güncelleyebilmelidir. Kredi kartıyla ödeme alındığında kullanıcıya kredi kartını kaydedelim mi? Sorusu yöneltiniz. Kaydetmek isteyen müşteriye sonraki ödemede kayıtlı kredi kartını gösteriniz.
10. Markalar tekrar edemez. (Brand-add)
11. (Düzeltme) Kredi kartı formatı control edilmeli. Regex
12. `Arabalar bakıma gönderilebilmelidir`. Kirada olan bir araba bakıma gönderilemez. `Bakımda olan bir araba kiralanamaz`. Bakımda olan araba tüm listelerde listelenmemelidir.
13. Şirketimiz büyüdü. Kurumsal müşteriler araba kiralayabilmelidir. `(Kurumsal Müşteri – vergiNo, Şirket ismi,email….` Kiralama kuralları bireysel müşteri ile aynıdır.
14. Tüm kiralamalar sonucunda fatura kesilmelidir. (`Fatura No, Oluşturma Tarihi, Kiralama tarihleri, Toplam kiralama gün sayısı, kiralama tutarı`) Belirli tarih aralığında tüm faturalar listelenebilmelidir. Müşteriye ait faturalar listelenebilmelidir.
15. Farklı illerde şubeler açmaya karar verdik. `Arabalara şehir bilgisi eklenmelidir`. Şehir bilgisine göre arabalar listelenebilmelidir. `Kiralamada alış şehri – dönüş şehri bilgisi eklenmelidir`. Araba teslim edildiğinde, dönüş şehri farklıysa, araba dönüş ili portföyüne girmelidir.
16. Arabalara `güncel kilometre bilgisi` eklenmelidir. Kiralamalarda başlıngıç kilometresi girilmelidir. `Kiralama Dönüşlerinde dönüş kilometresi bilgisi` girilmelidir. Bu bilgi arabada da güncellenmelidir.
17. Arabaya ait `hasarlar` girilebilmelidir. `(Id,CarId,HasarBilgisi)`
18. `Fake Pos Servisi ekleyiniz`. Servis olumsuz döndüğünde kiralama gerçekleşmemelidir.
19. Araba `farklı ilde teslim edildiğinde kiralama bedeline 500 TL` ek hizmet bedeli eklenmelidir.
20. Hizmet kalitemizi arttırmaya karar verdik. Araba kiralanırken ek hizmetler satin alınabilir. `Bebek koltuğu, scooter …. `Günlük bedelleri üzerinden kiralamaya eklenmelidir.
21. Ek hizmetler `eklenebilmeli`, `güncellenebilmeli`, `listelenebilmelidir`. Ek hizmetler isim olarak tekrar edemez.


## Technologies
- Technologies that used in this project are:

#### 💻 Frameworks 
![SpringBoot](https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![Swagger](https://img.shields.io/badge/Swagger-85EA2D?style=for-the-badge&logo=Swagger&logoColor=white)
![Maven](https://img.shields.io/badge/apache_maven-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white)

#### 💻 Code Language
![](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)

#### 🛢 Database
![](https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white)

#### 💻 IDE
![](https://img.shields.io/badge/Eclipse-2C2255?style=for-the-badge&logo=eclipse&logoColor=white)


## Linkedin
![](https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white)
* [Berkay ERAYDIN](https://www.linkedin.com/in/berkayeraydin/)
* [İbrahin GEZER](https://www.linkedin.com/in/ibrahimgezer92/)

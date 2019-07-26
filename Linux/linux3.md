Linux3
=====

> 날짜 : 19.07.26

## 하드디스크 관리
### IDE vs SCSI

#### IDE
- 일반적인 하드디스크, CD/DVD 장치에 사용됨
- 슬롯 당 2개까지 장착 가능
- 최근 PC에는 하드디스크 방식이 SATA(Serial ATA)식

#### SCSI
- 서버용 하드디스크에 채택되는 방식
- 슬롯 당 최대 16개까지 장착 가능
- 최근에는 SA-SCSI(Serial Attached SCSI)를 이용해 65,535개까지 연결 가능

### 하드디스크 추가
1. 물리적인 하드디스크 장착
2. `fdisk`를 통해 파티션 할당
3. `mkfs`를 통해 파일시스템 생성(formatting)
4. 하드디스크에 실제 사용 할 디렉토리를 mount

<iframe
  src="https://carbon.now.sh/embed/?bg=rgba(171%2C%20184%2C%20195%2C%201)&t=lucario&wt=none&l=text%2Fx-java&ds=true&dsyoff=20px&dsblur=68px&wc=true&wa=true&pv=56px&ph=56px&ln=false&fm=Hack&fs=14px&lh=133%25&si=false&es=2x&wm=false"
  style="transform:scale(0.7); width:1024px; height:473px; border:0; overflow:hidden;"
  sandbox="allow-scripts allow-same-origin">
</iframe>
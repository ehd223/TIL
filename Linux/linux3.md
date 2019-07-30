하드디스크 관리와 RAID
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

## RAID
> RAID(Redundant Array of Independent/Inexpensive Disks)
여러 개의 하드디스크를 하나의 하드디스크처럼 사용하는 방식.
효과 : 비용 절감, 신뢰성 및 성능 향상

### RAID 레벨

1. Linear RAID
    - 최소 두 장의 하드디스크가 필요
    - 연결된 하드디스크들을 하나의 볼륩으로 사용
    - 순차적으로 앞 하드디스크부터 채워나가는 방식.


2. RAID 0
    - 
3. RAID 1
4. RAID 2
5. RAID 3
5. RAID 4
6. RAID 5
7. RAID 6
8. 혼합 방식

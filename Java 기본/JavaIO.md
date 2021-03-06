Java IO 정리
=========

> 날짜 : 19.09.06

1주차 신입교육 과제(DBnFile)를 하면서 기본적인 입출력에 대한 것을 정리해보았다.

## IO 클래스

자바의 모든 입출력은 Stream이라는 단위로 이루어진다.
> Stream : 단 방향의 연속적인 데이터 흐름

아래는 자바의 IO 클래스들을 간략하게 그려본 것.

![java io classes](./imgs/javaIO.png)

IO클래스는 크게 두 가지로 나뉜다.
- Byte stream
  - Byte를 기준으로 IO 수행
  - Byte를 단위로 하기때문에 다양한 형태의 데이터를 입출력하는데에 사용됨.
  - 최상위 추상클래스는 `InputStream` / `OutputStream` 두 가지
- Character stream
  - Character를 기준으로 IO수행
  - 문자에 특화되어있는 IO Stream Class
  - byte단위를 유니코드와 매핑하여 리턴해주는 기본 기능을 가지고 있음.
  - 최상위 추상클래스는 `Reader` / `Writer`

### ByteStream 예시
```java
public class FileReading{
    public static void main(String args){
        File inputFile = new File("입력 파일 경로");
        File outputFile = new File("출력 파일 경로");

        try{
            // InputStream 객체를 FileInputStream 클래스로 구현
            // BufferedInputStream을 거치는 이유는 아래에 설명!
            InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
            OutputStream outputStream = new FileOutputStream(outputFile);

            // EOF까지 1byte 단위로 읽어서 출력
            byte b = 0;
            while((b = inputStream.read()) != -1){
                outputStream.write(b);  
            }
        } catch(IOException e){
            e.printStackTrace();
        }

        inputFile.close();
        outputFile.close();
    }
}
```

inputStream을 FileInputStream으로 구현하여 파일을 1 byte 단위로 읽고,
outputStream을 FileOutputStream으로 구현하여 읽어온 것을 다시 1 byte 단위로 쓰는 예시.

**<span style="font-color:red">중요!</span> FileInputStream을 BufferedInputStream으로 wrapping한 이유**

- Java는 InputStream을 통해 1byte씩 읽어오지만, 운영체제는 512byte 또는 1024byte 단위로 읽게됨
- Java에서 시스템(OS)에게 데이터를 가져다 달라해서 1024개를 들고갔더니 그 중에 하나만 가져가고 다시 또 부르고.. 
- 이런 매우 비효율적인 작업을 해결하기 위해 버퍼를 사용하고자 함.
- BufferedInputStream 클래스를 이용하여 시스템에서 가져온 만큼 버퍼에 넣고, 버퍼에서 호출지점으로 1byte씩 리턴 -> 훨씬 효율적


### CharacterStream 사용 예시
```java
public class FileReading{
    public static void main(String args){
        File inputFile = new File("입력 파일 경로");
        File outputFile = new File("출력 파일 경로");

        try{
            // Reader 객체를 FileReader 클래스로 구현
            // Writer 객체를 FileWriter 클래스로 구현
            Reader reader = new BufferedReader(new FileReader(inputFile));
            Writer writer = new FileWriter(outputFile);

            // EOF까지 2yte 단위로 읽어서 출력
            int b = 0;
            while((b = reader.read()) != -1){
                writer.write((char) b);
            }
        } catch(IOException e){
            e.printStackTrace();
        }

        inputFile.close();
        outputFile.close();
    }
}
```
byte stream과 거의 비슷함.

차이점은 1byte씩 읽는지, 2byte씩 읽는지.

FileReader를 BufferedReader로 wrapping한 이유도 동일함.
> **Buffer를 이용할 때 주의사항**<br>
> 많은 양의 데이터를 한 번에 버퍼에 넣고 write나 out을 할 경우, 버퍼의 용량이 초과된다면 데이터가 짤릴 수 있다. 그러므로, 일정 주기를 기준으로 버퍼를 비워주는 것이 좋다.
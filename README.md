# tracer
- 索引，是为了加快信息主体查找过程，基于目标信息主体，预先创建的一种储存结构。
- 倒排索引（Inverted index），是一种索引方法，基于信息主体的属性值进行构建。
- 本工程基于Map+BitSet结构，采用：属性->属性值->信息主体唯一标识，预先构建倒排索引，加速查找过程。

## 索引存储

定义标签属性【Brand，Color，Size】

标签A

| 属性名        | 属性值           |
| ------------- | ---------------- |
| Brand（品牌） | AAA              |
| Color（颜色） | Blue，Red，White |
| Size（尺寸）  | M，L             |

标签B

| 属性名        | 属性值      |
| ------------- | ----------- |
| Brand（品牌） | BBB         |
| Color（颜色） | Blue，Black |
| Size（尺寸）  | M，L，XL    |

```mermaid
graph LR
Brand[Brand] 
Brand[Brand]-.-B_Value1[AAA]
Brand[Brand]-.-B_Value2[BBB]
B_Value1[AAA]-->TagB1[TagA]
B_Value2[BBB]-->TagB2[TagB]
TagB1[TagA]-->BitB1[1,0]
TagB2[TagB]-->BitB2[0,1]
Color[Color] 
Color[Color]-.-C_Value1[Blue]
Color[Color]-.-C_Value2[Red]
Color[Color]-.-C_Value3[White]
Color[Color]-.-C_Value4[Black]
C_Value1[Blue]-->TagC1[TagA,TagB]
C_Value2[Red]-->TagC2[TagA]
C_Value3[White]-->TagC3[TagA]
C_Value4[Black]-->TagC4[TagB]
TagC1[TagA,TagB]-->BitC1[1,1]
TagC2[TagA]-->BitC2[1,0]
TagC3[TagA]-->BitC3[1,0]
TagC4[TagB]-->BitC4[0,1]
Size[Size]
Size[Size] -.-S_Value1[M]
Size[Size] -.-S_Value2[L]
Size[Size] -.-S_Value3[XL]
S_Value1[M]-->TagS1[TagA,TagB]
S_Value2[L]-->TagS2[TagA,TagB]
S_Value3[XL]-->TagS3[TagB]
TagS1[TagA,TagB]-->BitS1[1,1]
TagS2[TagA,TagB]-->BitS2[1,1]
TagS3[TagB]-->BitS3[0,1]
```


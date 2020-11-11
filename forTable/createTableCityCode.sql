USE [WebProject]
GO

/****** Object:  Table [dbo].[CityCode]    Script Date: 2020/11/6 �W�� 11:14:46 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[CityCode](
	[city_code] [char](3) NOT NULL,
	[city_name] [nvarchar](3) NOT NULL,
 CONSTRAINT [PK_CityCode] PRIMARY KEY CLUSTERED 
(
	[city_code] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO

INSERT INTO [dbo].[CityCode] ([city_code], [city_name])
     VALUES ('t01', '�O�_��'),
			('t02', '�s�_��'),
			('t03', '��饫'),
			('t04', '�O����'),
			('t05', '�O�n��'),
			('t06', '������'),
			('t07', '�򶩥�'),
			('t08', '�s�˥�'),
			('t09', '�Ÿq��'),
			('t10', '�s�˿�'),
			('t11', '�]�߿�'),
			('t12', '���ƿ�'),
			('t13', '�n�뿤'),
			('t14', '���L��'),
			('t15', '�Ÿq��'),
			('t16', '�̪F��'),
			('t17', '�y����'),
			('t18', '�Ὤ��'),
			('t19', '�O�F��'),
			('t20', '���'),
			('t21', '������'),
			('t22', '�s����')
GO



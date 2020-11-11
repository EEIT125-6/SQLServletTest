USE [WebProject]
GO

/****** Object:  Table [dbo].[CityCode]    Script Date: 2020/11/6 上午 11:14:46 ******/
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
     VALUES ('t01', '臺北市'),
			('t02', '新北市'),
			('t03', '桃園市'),
			('t04', '臺中市'),
			('t05', '臺南市'),
			('t06', '高雄市'),
			('t07', '基隆市'),
			('t08', '新竹市'),
			('t09', '嘉義市'),
			('t10', '新竹縣'),
			('t11', '苗栗縣'),
			('t12', '彰化縣'),
			('t13', '南投縣'),
			('t14', '雲林縣'),
			('t15', '嘉義縣'),
			('t16', '屏東縣'),
			('t17', '宜蘭縣'),
			('t18', '花蓮縣'),
			('t19', '臺東縣'),
			('t20', '澎湖縣'),
			('t21', '金門縣'),
			('t22', '連江縣')
GO



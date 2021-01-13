CREATE TABLE [dbo].[TIENCHI](
	[Ma_chi] [int] IDENTITY(100,1) NOT NULL,
	[Danh_muc] [nvarchar](50) NOT NULL,
	[Tien_chi] [int] NOT NULL,
	[Thoi_gian] [date] NOT NULL,
	[Ghi_chu] [nvarchar](50) NULL
) ON [PRIMARY]
GO

CREATE TABLE [dbo].[TIENTHU](
	[Ma_thu] [int] IDENTITY(100,1) NOT NULL,
	[Danh_muc] [nvarchar](50) NOT NULL,
	[Tien_thu] [int] NOT NULL,
	[Thoi_gian] [date] NOT NULL,
	[Ghi_chu] [nvarchar](50) NULL
) ON [PRIMARY]
GO



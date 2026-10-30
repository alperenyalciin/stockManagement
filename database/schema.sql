USE [StockDB]
GO

CREATE TABLE [dbo].[Products](
    [ProductID] [int] IDENTITY(1,1) NOT NULL,
    [NAME] [nvarchar](100) NOT NULL,
    [PRICE] [decimal](10, 2) NOT NULL,
    [QUANTITY] [int] NOT NULL,
    PRIMARY KEY CLUSTERED
(
    GO
    CREATE TABLE StockMovements
    (
    Id INT IDENTITY(1,1) PRIMARY KEY,
    ProductId INT NOT NULL,
    MovementType VARCHAR(10) NOT NULL,
    Quantity INT NOT NULL,
    MovementDate DATETIME2(7) DEFAULT SYSDATETIME(),

    CONSTRAINT FK_StockMovements_Products
    FOREIGN KEY (ProductId)
    REFERENCES Products(ProductID),

    CONSTRAINT CK_StockMovements_MovementType
    CHECK (MovementType IN ('IN', 'OUT')),

    CONSTRAINT CK_StockMovements_Quantity
    CHECK (Quantity > 0)
    );
    GO


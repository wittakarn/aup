interface DefaultSetting {
    sheetName: string,
    startRow: number;
    [key: string]: any;
}

export interface DataSetting extends DefaultSetting {
    link: string,
    asin: string,
    oldData: string,
    newData: string,
    asinCrawling: string,
    reviewScore: string,
    review: string,
    sellerName1: string,
    price1: string,
    shipping1: string,
    addOn1: string,
    type1: string,
    wid1: string,
    sellerName2: string,
    price2: string,
    shipping2: string,
    addOn2: string,
    type2: string,
    wid2: string,
    sellerName3: string,
    price3: string,
    shipping3: string,
    addOn3: string,
    type3: string,
    wid3: string,
}

export interface ApiSetting extends DefaultSetting {
    updateStatus: string,
    sku: string,
    asinApi: string,
    priceApi: string,
    minAllowPrice: string,
    maxAllowPrice: string,
    quantity: string,
    leadTime: string,
}

export interface SheetSettingPK {
    seq: number,
}

export interface SheetSetting {
    sheetSettingPK: SheetSettingPK,
    sheetId: string,
}

export interface Setting {
    sheetId: string,
    sheetTab: string,
    startRow: number,
    staffName: string,
    vendor: string,
    shippingName: string,
    shippingLastname: string,
    address1: string,
    address2: string,
    city: string,
    state: string,
    zip: string,
    phone: string,
    toBuy: string,
    payToVendor: string,
    orderProcess: string,
    vendorId: string,
}

export interface UserSetting {
    amazonUser: string;
    amazonPassword: string;
    befrugalUser: string;
    befrugalPassword: string;
}

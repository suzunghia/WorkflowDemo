export interface IProduct {
   ProductID: number;
   ProductName: string;
};

export class Product implements IProduct{
    constructor(public ProductID: number, public ProductName: string){}
}
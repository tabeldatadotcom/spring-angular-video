export class Order{
    id!: number;
    customerId!: number;
    product!: string;
    quantity!: number;
}

export class SaveOrder{
    customerId!: number;
    product!: string;
    quantity!: number;
}
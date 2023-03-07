import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Order } from 'src/app/model/order';
import { HomeService } from '../home.service';

@Component({
  selector: 'app-list-order',
  templateUrl: './list-order.component.html',
  styleUrls: ['./list-order.component.scss'],
})
export class ListOrderComponent implements OnInit {
  listOrder: Order[] = [];
  customerId!: number;
  customerName!: string;

  constructor(
    private service: HomeService,
    private router: Router,
    private activatedRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.activatedRoute.params.subscribe((params) => {
      console.log(params);
      this.customerId = params['id'];
    });

    this.getCustomerInfo();
    this.getListOrder();
  }

  getCustomerInfo() {
    this.service.findByCustomerId(this.customerId).subscribe((resp) => {
      console.log(resp.body);
      this.customerName = resp.body!.firstName;
    });
  }

  getListOrder() {
    this.service.getListOrderByCustomer(this.customerId).subscribe((resp) => {
      console.log(resp);
      this.listOrder = resp.body!;
    });
  }

  editOrder(id: any) {
    this.service.findByOrderId(id).subscribe((resp) => {
      console.log('hasil');
    });
  }

  deleteOrder(id: any) {
    this.service.deleteOrder(id).subscribe((resp) => {
      this.getCustomerInfo();
      this.getListOrder();
    });
  }
}

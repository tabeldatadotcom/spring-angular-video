import { Component, OnInit } from '@angular/core';
import { Customer } from 'src/app/model/customer';
import { HomeService } from '../home.service';

@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.scss']
})
export class ListComponent implements OnInit {

  listCustomer: Customer[] = [
    // https://mdbootstrap.com/img/new/avatars/8.jpg
  ];

  constructor(private service: HomeService) { }

  ngOnInit(): void {
    this.getList();
  }

  getList(){
    this.service.getList().subscribe(resp => {
      console.log(resp.body);
      this.listCustomer = resp.body!;
    })
  }

}

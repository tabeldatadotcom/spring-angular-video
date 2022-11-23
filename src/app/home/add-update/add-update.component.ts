import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { HomeService } from '../home.service';

@Component({
  selector: 'app-add-update',
  templateUrl: './add-update.component.html',
  styleUrls: ['./add-update.component.scss']
})
export class AddUpdateComponent implements OnInit {

  formInputCustomer!: FormGroup;
  formInputOrder!: FormGroup;
  imageFile!: File;
  imageSrc: string = "https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-chat/ava6.webp";
  type!: string;
  customerId!: number;

  constructor(
    private formBuilder: FormBuilder,
    private service: HomeService,
    private router: Router,
    private activatedRoute: ActivatedRoute) { }

  ngOnInit(): void {

    this.activatedRoute.params.subscribe(param => {
      this.customerId = param['id']
      if (param['id']) {
        this.type = 'order'
      } else {
        this.type = 'customer'
      }
      console.log(`customer id : ${param['id']}`)
      console.log(`customer id : ${this.customerId}`)
    })

    this.formInputCustomer = this.formBuilder.group({
      firstName: this.formBuilder.control(null),
      lastName: this.formBuilder.control(null),
      address: this.formBuilder.control(null),
      city: this.formBuilder.control(null),
      imageUrl: this.formBuilder.control(null)
    })

    this.formInputOrder = this.formBuilder.group({
      customerId: this.formBuilder.control(this.customerId),
      product: this.formBuilder.control(null),
      quantity: this.formBuilder.control(null)
    })
  }

  onFileChange(event: any){
    const reader = new FileReader();

    if(event.target.files && event.target.files.length) {

      const [file] = event.target.files;

      this.imageFile = file;

      reader.readAsDataURL(file);
      reader.onload = () => {
        this.imageSrc = reader.result as string;
      };
    }
  }

  save() {
    if (this.type == 'customer') {

      this.service.saveImage(this.imageFile).subscribe(resp => {
        if (resp.status == 200 || resp.status == 201) {
          console.log(`berhasil ${resp.body.imageUrl}`);
          this.formInputCustomer.patchValue({
            imageUrl: resp.body.imageUrl
          })

          this.service.save(this.formInputCustomer.value).subscribe(resp => {
            if (resp.status == 200 || resp.status == 201) {
              this.router.navigate(['']);
            }
          })

        }
      })

    } else {
      this.service.saveOrder(this.formInputOrder.value).subscribe(resp => {
        if (resp.status == 200) {
          this.router.navigate(['order', this.customerId]);
        }
      })
    }

  }

}

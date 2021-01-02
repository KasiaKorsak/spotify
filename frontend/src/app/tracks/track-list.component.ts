import { Component, OnInit } from '@angular/core';
import {Track} from './track'
import {TrackService} from './track.service'

@Component({
    templateUrl: 'track-list.component.html'
})
export class TrackListComponent{
    pageTitle='Tracks';
    _authorName: string='';
    tracks:Track[]=[];
    imageWidth: number = 50;
    imageMargin: number = 2;

    constructor(private service: TrackService){
        // this.authorName='sia'
    }


    get authorName(): string {
        return this._authorName;
      }
      set authorName(value: string) {
        this._authorName = value;
        // this.filteredProducts = this.listFilter ? this.performFilter(this.listFilter) : this.products;
      }

    search(): void{
        this.service.getTracks(this.authorName).subscribe({
            next: tracks => {
                this.tracks = tracks;
            },
            error: err => console.log(err)
        })

        // this.productService.getProducts().subscribe({
        //     next: products => {
        //       this.products = products;
        //       this.filteredProducts = this.performFilter(this.listFilter);
        //     },
        //     error: err => this.errorMessage = err
        //   });
    }
}
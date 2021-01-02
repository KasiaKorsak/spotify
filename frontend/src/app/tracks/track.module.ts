import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { TrackListComponent } from './track-list.component';
import { FormsModule } from '@angular/forms';

@NgModule({
    imports:[RouterModule.forChild([
        {path:'tracks', component: TrackListComponent}
    ]), 
    FormsModule],
    declarations:[TrackListComponent]
})

export class TrackModule{}
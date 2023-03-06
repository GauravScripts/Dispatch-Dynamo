import { Prediction } from '../prediction';
import { Component, OnInit, ViewChild, ElementRef, AfterViewInit } from '@angular/core';
import * as mobilenet from '@tensorflow-models/mobilenet';
import * as tf from '@tensorflow/tfjs';

@Component({
  selector: 'app-classifier-camera',
  templateUrl: './classifier-camera.component.html',
  styleUrls: ['./classifier-camera.component.css']
})
export class ClassifierCameraComponent implements OnInit, AfterViewInit {

  @ViewChild('video') video: ElementRef;
  predictions: Prediction[];
  model: any;
  loading = true;
  constructor() { }

  async ngOnInit() {

    this.model = await mobilenet.load();
    this.loading = false;

    setInterval(async () => {
      this.predictions = await this.model.classify(this.video.nativeElement);
      await tf.nextFrame();
    }, 3000);
  }
  async ngAfterViewInit() {
    const vid = this.video.nativeElement;
    const isMobile = /iPhone|iPad|iPod|Android/i.test(navigator.userAgent);

    if (navigator.mediaDevices.getUserMedia && !isMobile) {
      navigator.mediaDevices.getUserMedia({ video: { facingMode : {exact: 'user'}}})
        .then((stream) => {
          vid.srcObject = stream;
        })
        .catch((err0r) => {
          console.log('Something went wrong!');
        });
    } else {
      navigator.mediaDevices.getUserMedia({ video: { facingMode : {exact: 'environment'}}})
      .then((stream) => {
        vid.srcObject = stream;
      })
      .catch((err0r) => {
        console.log('Something went wrong!');
      });
    }
  }
}

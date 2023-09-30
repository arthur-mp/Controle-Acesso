import { Injectable } from '@angular/core';
import { DatePipe } from '@angular/common';

@Injectable({
  providedIn: 'root',
})
export class DateTimeFormatService {
  constructor(private datePipe: DatePipe) {}

  formatToDateString(date: Date): string {
    return this.datePipe.transform(date, 'yyyy-MM-dd') || '';
  }

  formatToTimeString(time: Date): string {
    return this.datePipe.transform(time, 'HH:mm:ss') || '';
  }
}

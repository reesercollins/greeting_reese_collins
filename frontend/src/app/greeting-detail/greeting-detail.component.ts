import { Component, Input } from '@angular/core';
import { Greeting } from '../greeting';
import { GreetingService } from '../greeting.service';

@Component({
  selector: 'app-greeting-detail',
  templateUrl: './greeting-detail.component.html',
  styleUrls: ['./greeting-detail.component.css']
})
export class GreetingDetailComponent {
  @Input() greeting?: Greeting;

  constructor(private greetingService: GreetingService) { }

  save(): void {
    if (this.greeting) {
      this.greetingService.updateGreeting(this.greeting).subscribe()
    }
  }
}

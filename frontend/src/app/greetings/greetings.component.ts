import { Component } from '@angular/core';
import { Greeting } from '../greeting';
import { GreetingService } from '../greeting.service';

@Component({
  selector: 'app-greetings',
  templateUrl: './greetings.component.html',
  styleUrls: ['./greetings.component.css']
})
export class GreetingsComponent {

  constructor(private greetingService: GreetingService) { }

  getGreetings(): void {
    this.greetingService.getGreetings().subscribe(greetings => this.greetings = greetings);
  }

  ngOnInit(): void {
    this.getGreetings();
  }

  onSelect(greeting: Greeting) {
    this.selectedGreeting = greeting;
  }

  add(name: string): void {
    name = name.trim();
    this.greetingService.addGreeting(name).subscribe(greeting => {
      this.greetings.push(greeting);
    })
  }

  greetings: Greeting[] = [];
  selectedGreeting?: Greeting;
  name: string = "";
}

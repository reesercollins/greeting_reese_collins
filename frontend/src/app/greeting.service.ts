import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, of, tap } from 'rxjs';
import { Greeting } from './greeting';

@Injectable({
  providedIn: 'root'
})
export class GreetingService {

  private greetingUrl = '/api/greeting';

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(private http: HttpClient) { }

  getGreetings(): Observable<Greeting[]> {
    return this.http.get<Greeting[]>(this.greetingUrl).pipe(
      catchError(this.handleError<Greeting[]>('getGreetings', []))
    );
  }

  updateGreeting(greeting: Greeting): Observable<any> {
    const url = `${this.greetingUrl}?id=${greeting.id}`;
    const body = {
      content: greeting.content
    };
    return this.http.put(url, body, this.httpOptions).pipe(
      catchError(this.handleError('updateGreeting'))
    );
  }

  addGreeting(name: string): Observable<any> {
    const url = `${this.greetingUrl}?name=${name}`;
    return this.http.post(url, {}).pipe(
      catchError(this.handleError('addGreeting'))
    );
  }

  /**
   * Handle Http operation that failed.
   * Let the app continue.
   *
   * @param operation - name of the operation that failed
   * @param result - optional value to return as the observable result
   */
  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {

      // TODO: send the error to remote logging infrastructure
      console.error(error); // log to console instead

      // Let the app keep running by returning an empty result.
      return of(result as T);

    };
  }
}

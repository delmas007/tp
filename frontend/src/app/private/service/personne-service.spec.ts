import { TestBed } from '@angular/core/testing';

import { PersonneService } from './personne-service';
import {HttpClientTestingModule} from '@angular/common/http/testing';

describe('PersonneService', () => {
  let service: PersonneService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule]
    });
    service = TestBed.inject(PersonneService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

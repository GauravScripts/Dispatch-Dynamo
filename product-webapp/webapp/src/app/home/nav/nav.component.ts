import { Component, ViewEncapsulation } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';
import { Observable } from 'rxjs';
import { DarkModeService } from 'angular-dark-mode';
import { Router } from '@angular/router';

@Component({
  selector: 'app-nav',
  templateUrl: './nav.component.html',
  styleUrls: ['./nav.component.scss'],
  encapsulation: ViewEncapsulation.None
})
export class NavComponent {
  darkMode$: Observable<boolean> = this.darkModeService.darkMode$;

  constructor(
      public translateService: TranslateService,
      private darkModeService: DarkModeService,
      private router: Router
  ) {
    translateService.addLangs(['eng', 'hin', 'mrthi', 'tamil', 'telugu']);
    translateService.setDefaultLang('eng');
  }

  switchLanguage(language: string) {
    this.translateService.use(language);
    this.translateService.setDefaultLang(language);
  }

  onToggle(): void {
    this.darkModeService.toggle();
  }

  userlogin() {
    this.router.navigate(['/getStarted']);
  }
}

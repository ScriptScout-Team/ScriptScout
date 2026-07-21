import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { LayoutComponent } from './layout/layout.component';


import { LoginComponent } from './pages/login/login.component';
import { SignupComponent } from './pages/signup/signup.component';
import { ForgotPasswordComponent } from './pages/forgot-password/forgot-password.component';
import { ResetPasswordComponent } from './pages/reset-password/reset-password.component';
import { SettingsComponent } from './pages/settings/settings.component';
import { DashboardComponent } from './pages/dashboard/dashboard.component';
import { UploadComponent } from './pages/upload/upload.component';
import { ProcessingQueueComponent } from './pages/processing-queue/processing-queue.component';
import { VideoDetailsComponent } from './pages/video-details/video-details.component';
import { ProfileComponent } from './pages/profile/profile.component';
import { MediaLibraryComponent } from './pages/media-library/media-library.component';
import { EditVideoComponent } from './pages/edit-video/edit-video.component';
import { VideoPlayerComponent } from './pages/video-player/video-player.component';
import { MetadataEditorComponent } from './pages/metadata-editor/metadata-editor.component';
import { TranscriptComponent } from './pages/transcript/transcript.component';
import { ChapterBreakdownComponent } from './pages/chapter-breakdown/chapter-breakdown.component';
import { SearchCatalogComponent } from './pages/search-catalog/search-catalog.component';

const routes: Routes = [
  // Pages WITHOUT Sidebar/Navbar
  
   { path: '', redirectTo: 'login', pathMatch: 'full' },

  {
    path: 'login',
    component: LoginComponent,
  },

  {
    path: 'signup',
    component: SignupComponent,
  },

  {
    path: 'forgot-password',
    component: ForgotPasswordComponent,
  },

  {
    path: 'reset-password',
    component: ResetPasswordComponent,
  },

  // Pages WITH Sidebar/Navbar

  {
    path: '',
    component: LayoutComponent,

    children: [
      {
        path: 'dashboard',
        component: DashboardComponent,
      },

      {
        path: 'upload',
        component: UploadComponent,
      },

      {
        path: 'video-list',
        component: ProcessingQueueComponent,
      },

      {
        path: 'video/:id',
        component: VideoDetailsComponent,
      },

      {
        path: 'profile',
        component: ProfileComponent,
      },

      {
        path: 'media-library',
        component: MediaLibraryComponent,
      },

      {
        path: 'edit-video/:id',
        component: EditVideoComponent,
      },

      {
        path: 'video-player/:id',
        component: VideoPlayerComponent,
      },

      {
        path: 'metadata-editor/:id',
        component: MetadataEditorComponent,
      },

      {
        path: 'transcript/:id',
        component: TranscriptComponent,
      },

      {
        path: 'chapter-breakdown/:id',
        component: ChapterBreakdownComponent,
      },
      {
        path: 'processing-queue',
        component: ProcessingQueueComponent,
      },
      {
  path: 'settings',
  component: SettingsComponent
},
{
  path:'search-catalog',
  component:SearchCatalogComponent
}
    ],
  },

  {
    path: '**',
    redirectTo: '',
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}

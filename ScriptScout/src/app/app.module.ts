import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './pages/login/login.component';
import { DashboardComponent } from './pages/dashboard/dashboard.component';
import { UploadComponent } from './pages/upload/upload.component';
import { ProcessingQueueComponent } from './pages/processing-queue/processing-queue.component';
import { VideoDetailsComponent } from './pages/video-details/video-details.component';
import { ProfileComponent } from './pages/profile/profile.component';
import { NavbarComponent } from './shared/navbar/navbar.component';
import { SidebarComponent } from './shared/sidebar/sidebar.component';
import { FooterComponent } from './shared/footer/footer.component';
import { FormsModule } from '@angular/forms';

import { SignupComponent } from './pages/signup/signup.component';
import { ForgotPasswordComponent } from './pages/forgot-password/forgot-password.component';
import { ResetPasswordComponent } from './pages/reset-password/reset-password.component';
import { MediaLibraryComponent } from './pages/media-library/media-library.component';
import { EditVideoComponent } from './pages/edit-video/edit-video.component';
import { VideoPlayerComponent } from './pages/video-player/video-player.component';
import { MetadataEditorComponent } from './pages/metadata-editor/metadata-editor.component';
import { TranscriptComponent } from './pages/transcript/transcript.component';
import { ChapterBreakdownComponent } from './pages/chapter-breakdown/chapter-breakdown.component';
import { ReportsComponent } from './pages/reports/reports.component';
import { LayoutComponent } from './layout/layout.component';
import { SettingsComponent } from './pages/settings/settings.component';
import { SearchCatalogComponent } from './pages/search-catalog/search-catalog.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    DashboardComponent,
    UploadComponent,
    ProcessingQueueComponent,
    VideoDetailsComponent,
    ProfileComponent,
    NavbarComponent,
    SidebarComponent,
    FooterComponent,
   
    SignupComponent,
    ForgotPasswordComponent,
    ResetPasswordComponent,
    MediaLibraryComponent,
    EditVideoComponent,
    VideoPlayerComponent,
    MetadataEditorComponent,
    TranscriptComponent,
    ChapterBreakdownComponent,
    ReportsComponent,
    LayoutComponent,
    SettingsComponent,
    SearchCatalogComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    BrowserAnimationsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

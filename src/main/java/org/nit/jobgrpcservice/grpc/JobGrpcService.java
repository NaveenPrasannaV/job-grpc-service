package org.nit.jobgrpcservice.grpc;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.nit.jobgrpc.*;
import org.nit.jobgrpcservice.model.JobPost;
import org.nit.jobgrpcservice.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@GrpcService
public class JobGrpcService extends JobServiceGrpc.JobServiceImplBase {

    @Autowired
    private JobService service;

    @Override
    public void getJob(JobRequest request,
                       StreamObserver<JobResponse> responseObserver) {

        JobPost job = service.getJob(request.getPostId());

        JobResponse response = mapToResponse(job);

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void getAllJobs(Empty request,
                           StreamObserver<JobList> responseObserver) {

        List<JobPost> jobs = service.getAllJobs();

        List<JobResponse> responses =
                jobs.stream().map(this::mapToResponse).toList();

        JobList list = JobList.newBuilder()
                .addAllJobs(responses)
                .build();

        responseObserver.onNext(list);
        responseObserver.onCompleted();
    }

    @Override
    public void addJob(JobResponse request,
                       StreamObserver<JobResponse> responseObserver) {

        JobPost job = mapToEntity(request);

        service.addJob(job);

        responseObserver.onNext(request);
        responseObserver.onCompleted();
    }

    @Override
    public void updateJob(JobResponse request,
                          StreamObserver<JobResponse> responseObserver) {

        JobPost job = mapToEntity(request);

        service.updateJob(job);

        responseObserver.onNext(request);
        responseObserver.onCompleted();
    }

    @Override
    public void deleteJob(JobRequest request,
                          StreamObserver<DeleteResponse> responseObserver) {

        service.deleteJob(request.getPostId());

        DeleteResponse response =
                DeleteResponse.newBuilder()
                        .setMessage("Deleted")
                        .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void searchJobs(SearchRequest request,
                           StreamObserver<JobList> responseObserver) {

        List<JobPost> jobs = service.search(request.getKeyword());

        List<JobResponse> responses =
                jobs.stream().map(this::mapToResponse).toList();

        JobList list =
                JobList.newBuilder()
                        .addAllJobs(responses)
                        .build();

        responseObserver.onNext(list);
        responseObserver.onCompleted();
    }

    private JobResponse mapToResponse(JobPost job) {

        return JobResponse.newBuilder()
                .setPostId(job.getPostId())
                .setPostProfile(job.getPostProfile())
                .setPostDesc(job.getPostDesc())
                .setReqExperience(job.getReqExperience())
                .addAllPostTechStack(job.getPostTechStack())
                .build();
    }

    private JobPost mapToEntity(JobResponse res) {

        return new JobPost(
                res.getPostId(),
                res.getPostProfile(),
                res.getPostDesc(),
                res.getReqExperience(),
                res.getPostTechStackList()
        );
    }
}